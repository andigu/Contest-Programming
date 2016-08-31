from ds.heap import BinHeap


class PriorityQueue:
    def __init__(self):
        self.heap = BinHeap()

    def insert(self, key, item):
        self.heap.push((key, item))

    def
