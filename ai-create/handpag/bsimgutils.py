"""
    想把脚本图像比较脚本化，方便使用
    比较方法（图像和存储图像比较法）

"""
from PIL import Image, ImageChops
from bsimgstuc import Rectangle
import pyautogui as pag

rect = Rectangle()
defaultPath = "C:/Users/愚者/PycharmProjects/pythonProject/pagtest/captureResource/spriteResource/"
sprite_name = ""


def judge_sprite_single(filename, show=True):
    global sprite_name
    path = defaultPath
    name = filename.split(".")[0].split("(")[0]
    ar = filename.split(".")[0].split("(")[1].split(")")[0].split(",")
    sprite_img = Image.open(path + filename)
    rect.setRect(int(ar[0]), int(ar[1]), int(ar[2]), int(ar[3]))
    size = (rect.x2 - rect.x1) * (rect.y2 - rect.y1)
    rate = diff_white_current_capture(sprite_img, rect)
    if show is True and rate is 1:
        print("结果", "精灵为{}".format(name))
        sprite_name = name
    return rate


def get_capture(a1, b1, a2, b2):
    if a2 >= a1:
        x2 = a2
        x1 = a1
    else:
        x2 = a1
        x1 = a2
    if b2 >= b1:
        y2 = b2
        y1 = b1
    else:
        y2 = b1
        y1 = b2
    print(x1, y1, x2, y2)
    rect.x1 = x1
    rect.y1 = y1
    rect.x2 = x2
    rect.y2 = y2
    img = pag.screenshot(region=(x1, y1, x2 - x1, y2 - y1))
    return img


def get_white_img(img):
    w = img.size[0]
    h = img.size[1]
    # 提取白色图像返回
    white_img = Image.new("RGB", (w, h))
    for row in range(w):
        for col in range(h):
            rgb = img.getpixel((row, col))
            r = rgb[0]
            g = rgb[1]
            b = rgb[2]
            if r == g and g == b:
                white_img.putpixel((row, col), rgb)
    return white_img


def get_limit_diff(img, limit):
    w = img.size[0]
    h = img.size[1]
    # 提取白色图像返回
    limit_img = Image.new("RGB", (w, h))
    for row in range(w):
        for col in range(h):
            rgb = img.getpixel((row, col))
            r = rgb[0]
            g = rgb[1]
            b = rgb[2]
            if r < limit and g < limit and b < limit:
                limit_img.putpixel((row, col), (0, 0, 0))
            else:
                limit_img.putpixel((row, col), rgb)
    return limit_img


def diff_white_current_capture(log_img, rectObj=None):
    global rect
    if rectObj is not None:
        rect = rectObj
    img = pag.screenshot(region=(rect.x1, rect.y1, rect.x2 - rect.x1, rect.y2 - rect.y1))
    # 比较白色部分差异
    white_img = get_white_img(img)
    if log_img is not None:
        white_log_img = get_white_img(log_img)
        # 比较差异图
        diff = ImageChops.difference(white_log_img, white_img)
        size = (rect.x2 - rect.x1) * (rect.y2 - rect.y1)
        histogram = diff.histogram()
        result_rate = ((histogram[0] + histogram[256] + histogram[512]) / 3.0) / size
        diff = get_limit_diff(diff, 5)
        histogram = diff.histogram()
        rate = ((histogram[0] + histogram[256] + histogram[512]) / 3.0) / size
        if diff is None or (histogram[0] == size and histogram[256] == size and histogram[512] == size):
            return 1
        elif rate > 0.99:
            return 1
        else:
            # print("r={},g={},b={},size={},相似率={}".format(histogram[0], histogram[256], histogram[512], size, rate))
            return rate


def diff_current_capture(log_img, rectObj=None):
    global rect
    if rectObj is not None:
        rect = rectObj
    img = pag.screenshot(region=(rect.x1, rect.y1, rect.x2 - rect.x1, rect.y2 - rect.y1))
    if log_img is not None:
        # 比较差异图
        diff = ImageChops.difference(log_img, img)
        histogram = diff.histogram()
        size = (rect.x2 - rect.x1) * (rect.y2 - rect.y1)
        if diff is None or (histogram[0] == size and histogram[256] == size and histogram[512] == size):
            return 1
        else:
            rate = ((histogram[0] + histogram[256] + histogram[512]) / 3.0) / size
            # print("r={},g={},b={},size={},相似率={}".format(histogram[0], histogram[256], histogram[512], size, rate))
            diff.save(
                "../../captureResource/diffResource/diff({},{},{},{}).png".format(rect.x1, rect.y1, rect.x2, rect.y2))
            return rate