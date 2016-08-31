class BinHeap:  # Min heap
    def __init__(self, lst=None):
        if lst is not None:
            self.heapify(lst)
        else:
            self.data = [0]  # never accessed (0th index just to make accessing simpler)
            self.size = 0

    def push(self, value):
        self.data.append(value)
        self.size += 1
        self.shift_up(self.size)

    def pop(self):
        smallest = self.data[1]
        self.data[1] = self.data[self.size]
        self.size -= 1
        self.data.pop()
        self.shift_down(1)
        return smallest

    def min_child(self, index):
        if index * 2 + 1 > self.size:
            return index * 2
        else:
            if self.data[index * 2] < self.data[index * 2 + 1]:
                return index * 2
            else:
                return index * 2 + 1

    def shift_up(self, index):
        while index // 2 > 0:
            if self.data[index] < self.data[index // 2]:
                temp = self.data[index // 2]
                self.data[index // 2] = self.data[index]
                self.data[index] = temp
            index //= 2

    def shift_down(self, index):
        while (index * 2) <= self.size:
            mc = self.min_child(index)
            if self.data[index] > self.data[mc]:
                tmp = self.data[index]
                self.data[index] = self.data[mc]
                self.data[mc] = tmp
            index = mc

    def peek(self):
        return self.data[1]

    def is_empty(self):
        return self.size == 0

    def heapify(self, lst):
        i = len(lst) // 2
        self.size = len(lst)
        self.data = [(0, 0)] + [tuple(i) for i in lst]
        while i > 0:
            self.shift_down(i)
            i -= 1
