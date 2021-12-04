import re

x = 0
y = 0
aim = 0
splittingRegex = '\s+'
with open('data.txt', 'r') as file:
	while not 0 == len(string := file.readline().strip()):
		split = re.split(splittingRegex, string)
		d = int(split[1])
		match split[0]:
			case 'forward':
				x += d
				y -= d * aim
			case 'up':
				aim -= d
			case 'down':
				aim += d
			case _:
				print('ERROR!')
				exit(1)

print(abs(x * y))