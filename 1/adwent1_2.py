import queue

incSum = 0
with open('data.txt', 'r') as file:
	window = queue.Queue(3)
	windowSum = 0
	for it in range(0,3):
		val = int(file.readline().strip())
		windowSum += val	
		window.put(val)
	while not 0 == len(string := file.readline().strip()):
		val = int(string)
		lastWindowSum = windowSum
		windowSum -= window.get()
		window.put(val)
		windowSum += val
		if(windowSum > lastWindowSum):
			incSum += 1

print(incSum)
