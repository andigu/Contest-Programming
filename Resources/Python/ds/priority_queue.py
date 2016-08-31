from ds.heap import BinHeap


class PriorityQueue:
    def __init__(self, data=None):
        self.heap = BinHeap(data)

    def push(self, key, item):
        self.heap.push((key, item))

    def pop(self):
        return self.heap.pop()

    def peek(self):
        return self.heap.peek()

    @property
    def is_empty(self):
        return self.heap.is_empty()
