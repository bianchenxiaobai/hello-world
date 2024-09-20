import turtle
import math

screen = turtle.Screen()
screen.bgcolor("white")
t = turtle.Turtle()
t.speed(1)

t.color("white")
t.goto(-50, 50+100/math.sqrt(2))


def draw_star():
    t.pensize(5)
    t.color("blue")
    t.begin_fill()

    for i in range(8):
        t.forward(100)
        t.right(45)

    t.end_fill()


draw_star()

t.hideturtle()
turtle.done()

