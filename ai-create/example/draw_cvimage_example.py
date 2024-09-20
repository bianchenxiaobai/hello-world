import cv2
import numpy as np
import math
img = cv2.imread("../resource/back.jpg") # 读取照片
# 红底化证件照
rows, cols, channels = img.shape
print(rows)
print(cols)
for i in range(rows):
    for j in range(cols):
        if i <= 600 and img[i, j][0] > 220 and img[i, j][1] > 220 and img[i, j][2] > 220:
            img[i, j] = [0, 27, 217]
img = cv2.bilateralFilter(img, 11, 21, 11)
minVal = 255
rgbVal = [0, 27, 217]
for i in range(rows):
    for j in range(cols):
        if i >= 250 and 200 <= j <= 400:
            None
        elif i <= 600 and img[i, j][0] > minVal and img[i, j][1] > minVal and img[i, j][2] > minVal \
                and abs(int(img[i, j][0])-int(img[i, j][1])) < 10 and abs(int(img[i, j][1])-int(img[i, j][2])) < 10\
                and abs(int(img[i, j][2])-int(img[i, j][0])) < 10:
            if i >= 10 and 10 <= j <= 590:
                img[i, j][0] = (int(img[i+1, j+1][0])+int(img[i-1, j+1][0])+int(img[i, j+1][0]) + int(img[i, j-1][0]))/4
                img[i, j][1] = (int(img[i+1, j+1][1])+int(img[i-1, j+1][1])+int(img[i, j + 1][1]) + int(img[i, j - 1][1])) / 4
                img[i, j][2] = (int(img[i+1, j+1][2])+int(img[i-1, j+1][2])+int(img[i, j + 1][2]) + int(img[i, j - 1][2])) / 4
            else:
                img[i, j] = rgbVal
blur = img
# 显示图像
# 窗口等待的命令，0表示无限等待
cv2.imshow('blur', blur)
cv2.imwrite("../resource/backblur.png", blur)
cv2.waitKey(0)
