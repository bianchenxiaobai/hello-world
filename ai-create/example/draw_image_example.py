import tkinter
from PIL import Image, ImageTk
"""
画一张背景图像的窗口
"""
root = tkinter.Tk()
# root.geometry('300x300')
cv = tkinter.Canvas(root, bg='white', width=400, height=300)
dog_img = Image.open('../resource/back.jpg')
img = ImageTk.PhotoImage(dog_img)
# draw
# 图像绘制在中心点附近
cv.create_image((200, 150), image=img)
# cv.create_rectangle(80,80,120,120,fill='black',outline='purple',dash=10)
cv.pack()

root.mainloop()
