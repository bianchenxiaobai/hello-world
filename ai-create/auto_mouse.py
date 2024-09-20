# -*- coding: utf-8 -*-
import random
import time
import tkinter as tk
from tkinter import Tk
import pyautogui as pag
# import thread

window = Tk()
window.title("auto-mouse")

screenWidth = window.winfo_screenwidth()
screenHeight = window.winfo_screenheight()
window.geometry("%dx%d+%d+%d" % (screenWidth / 4, screenHeight / 8, screenWidth / 4, screenHeight / 4))

StringWH = '' + str(screenWidth) + '-' + str(screenHeight)
frame = tk.Frame(window, height=300, width=500)
frame.grid()
tk.Label(frame, text=StringWH).grid(column=0, row=0)
tk.Label(frame, text="Click").grid(column=0, row=1, padx=20, pady=20)
rand = random.Random()


def judgeOutRange():
    x, y = pag.position()
    return y >= screenHeight / 6 * 5


def keypress(keyCharAlt):
    pag.keyDown(keyCharAlt)
    time.sleep(0.15 + 0.1 * rand.randint(0, 3))
    pag.keyUp(keyCharAlt)


def autoClickCount(totalCount):
    cntClick = 0
    for i in range(totalCount):
        if judgeOutRange() is True:
            break
        pag.click()
        #pag.click(button="right")
        #pag.click(button="middle")
        cntClick = cntClick + 1
        print("cnt="+str(cntClick))


def autoClickCountRight(totalCount):
    for i in range(totalCount):
        if judgeOutRange() is True:
            break
        pag.rightClick()
        time.sleep(0.08)


def autoClick(totalCount):
    if 1 <= totalCount <= 1000001:
        time.sleep(5)
#        thread.start_new_thread(autoClickCountRight, totalCount)
        autoClickCount(totalCount)


def autoClick100():
    autoClick(100)


def autoClick10K():
    autoClick(10000)


def autoClick1M():
    autoClick(1000000)


tk.Button(frame, text="Button100", command=autoClick100).grid(column=1, row=1)
tk.Button(frame, text="Button10K", command=autoClick10K).grid(column=2, row=1)
tk.Button(frame, text="Button1M", command=autoClick1M).grid(column=3, row=1)

window.mainloop()
