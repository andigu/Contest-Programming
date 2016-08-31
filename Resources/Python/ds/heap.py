class BinHeap:
    def __init__(self):
        self.heapList = [0] # header (not represented)
        self.currentSize = 0

    def insert(self, value):
        self.heapList.append(value)
        self.currentSize += 1

        # maintain heap property
        i = self.currentSize
        while i // 2 > 0:
            if self.heapList[i] < self.heapList[i // 2]: # swap parent and child
                temp = self.heapList[i // 2]
                self.heapList[i // 2] = self.heapList[i]
                self.heapList[i] = temp
            i //= 2

    def min_child(self, i):
        if i * 2 + 1 > self.currentSize:
            return i * 2
        else:
            if self.heapList[i * 2] < self.heapList[i * 2 + 1]:
                return i * 2
            else:
                return i * 2 + 1

    def delete_min(self):
        retval = self.heapList[1]
        self.heapList[1] = self.heapList[self.currentSize]
        self.currentSize -= 1
        self.heapList.pop()

        # maintain heap property
        i = 1
        while (i * 2) <= self.currentSize:
            mc = self.min_child(i)
            if self.heapList[i] > self.heapList[mc]:
                tmp = self.heapList[i]
                self.heapList[i] = self.heapList[mc]
                self.heapList[mc] = tmp
            i = mc

        return retval

    def build_heap(self, alist):
        i = len(alist) // 2
        self.currentSize = len(alist)
        self.heapList = [0] + alist[:]
        while i > 0:
            while (i * 2) <= self.currentSize:
                mc = self.min_child(i)
                if self.heapList[i] > self.heapList[mc]:
                    tmp = self.heapList[i]
                    self.heapList[i] = self.heapList[mc]
                    self.heapList[mc] = tmp
                i = mc
            i -= 1
