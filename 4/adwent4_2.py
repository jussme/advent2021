import re

def readNLines(file, n):
	lines = []
	for i in range(0, n):
		lines.append(file.readline().strip())
	return lines

def linesToGrid(lines):
	grid = []
	for line in lines:
		grid.append([int(strInt.strip()) for strInt in re.findall("\d+", line)])
	return grid

def mark(val, grid):
	found = False
	for row_i, row in enumerate(grid):
		for col_i, cell in enumerate(row):
			if cell == val:
				grid[row_i][col_i] = -1
				found = True
	return found

class Picker:
	def __init__(self, lineOfNumbers):
		self._picks = [int(strNumb) for strNumb in lineOfNumbers.split(',')]

	def pickNext(self):
		if len(self._picks) > 0:
			pick = self._picks[0]
			self._picks.pop(0)
			return pick	
		return None

def checkGrid(grid):
	#check columns
	for col in range(0, len(grid)):
		positiveFound = False
		for row in range(0, len(grid)):
			if(grid[row][col] >= 0):
				positiveFound = True
		if not positiveFound:
			return True

	#check rows
	for row in range(0, len(grid)):
		positiveFound = False
		for col in range(0, len(grid)):
			if(grid[row][col] >= 0):
				positiveFound = True
		if not positiveFound:
			return True
	return False

def countBoardScore(pick, grid):
	sum = 0
	for row in grid:
		for cell in row:
			if cell >= 0:
				sum += cell;
	return sum * pick

with open("data.txt", 'r') as file:
	picker = Picker(file.readline().strip())
	grids = []
	while len(line := file.readline()) != 0:
		fiveLines = readNLines(file, 5)
		grids.append(linesToGrid(fiveLines))
	
	wonGrids = []
	for i, grid in enumerate(grids):
		wonGrids.append(False)
	while not (pick := picker.pickNext()) == None:
		for i, grid in enumerate(grids):
			mark(pick, grid)
			if((not wonGrids[i]) and checkGrid(grid)):
				wonGrids[i] = True
				lastWinningScore = countBoardScore(pick, grid)

	print(lastWinningScore)