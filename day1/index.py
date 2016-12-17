#!/usr/bin/env python

import csv

def main():
    f = open('data.csv', 'rt')
    reader = csv.reader(f)
    for row in reader:
        data = row
    f.close()
    print 'part 1: '
    print part1(data)
    print 'part 2: '
    print part2(data)

def makeMove(direction, position, move):
    # Move in format ['L', 2] (turn, length)
    
    # [up, right, down, left]
    directions = [[0,1], [1,0], [0,-1], [-1,0]]
    
    # find new direction
    if move[0] == 'L':
        newDirection = (direction - 1) % 4
    else:
        newDirection = (direction + 1) % 4

    absoluteMove = [coord * move[1] for coord in directions[newDirection]]
    newPosition = [(position[0] + absoluteMove[0]), (position[1] + absoluteMove[1])]
    return [newDirection, newPosition]

def computeDistance(position):
    return abs(position[0]) + abs(position[1])

def makeStepwiseMove(direction, position, move):
    # Move in format ['L', 2] (turn, length)
    
    # [up, right, down, left]
    directions = [[0,1], [1,0], [0,-1], [-1,0]]
    
    # find new direction
    if move[0] == 'L':
        newDirection = (direction - 1) % 4
    else:
        newDirection = (direction + 1) % 4

    i = 0
    history = []
    newPosition = [position[0], position[1]]
    while (i < move[1]):
        newPosition = [(newPosition[0] + directions[newDirection][0]), (newPosition[1] + directions[newDirection][1])]
        history.append(newPosition)
        i += 1
    return [newDirection, newPosition, history]

def part1(data):
    position = [0,0]
    direction = 0
    for move in data:
        move = move.strip()
        updatedMove = makeMove(direction, position, [move[0], int(move[1:])])
        direction = updatedMove[0]
        position = updatedMove[1]

    return computeDistance(position)

def part2(data):
    position = [0,0]
    direction = 0
    history = [position]
    for move in data:
        move = move.strip()
        updatedMove = makeStepwiseMove(direction, position, [move[0], int(move[1:])])
        direction = updatedMove[0]
        position = updatedMove[1]
        for step in updatedMove[2]:
            try:
                history.index(step)
                secondVisit = computeDistance(step)
                return secondVisit
            except ValueError:
                history.append(step)

if __name__ == '__main__':
    main()
