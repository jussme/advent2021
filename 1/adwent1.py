incSum = 0
with open('data.txt', 'r') as file:
	lastVal = int(file.readline().strip())
	while not 0 == len(string := file.readline().strip()):
		val = int(string)
		if val > lastVal:
			incSum += 1
		lastVal = val

print(incSum)
		