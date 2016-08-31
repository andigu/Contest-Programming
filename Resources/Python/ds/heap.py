class BinHeap:  # Min heap
    def __init__(self):
        self.heapList = [0]  # never accessed (0th index just to make accessing simpler)
        self.currentSize = 0

    def push(self, value):
        self.heapList.append(value)
        self.currentSize += 1
        self.shift_up(self.currentSize)

    def pop(self):
        smallest = self.heapList[1]
        self.heapList[1] = self.heapList[self.currentSize]
        self.currentSize -= 1
        self.heapList.pop()
        self.shift_down(1)
        return smallest

    def min_child(self, index):
        if index * 2 + 1 > self.currentSize:
            return index * 2
        else:
            if self.heapList[index * 2] < self.heapList[index * 2 + 1]:
                return index * 2
            else:
                return index * 2 + 1

    def build_heap(self, lst):
        i = len(lst) // 2
        self.currentSize = len(lst)
        self.heapList = [0] + lst[:]
        while i > 0:
            self.shift_down(i)
            i -= 1

    def shift_up(self, index):
        while index // 2 > 0:
            if self.heapList[index] < self.heapList[index // 2]:
                temp = self.heapList[index // 2]
                self.heapList[index // 2] = self.heapList[index]
                self.heapList[index] = temp
            index //= 2

    def shift_down(self, index):
        while (index * 2) <= self.currentSize:
            mc = self.min_child(index)
            if self.heapList[index] > self.heapList[mc]:
                tmp = self.heapList[index]
                self.heapList[index] = self.heapList[mc]
                self.heapList[mc] = tmp
            index = mc
