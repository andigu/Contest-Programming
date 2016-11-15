class BinaryIndexedTree:
    def __init__(self, array):
        self.array = [0] + array
        self.size = len(self.array)
        for i in range(1, self.size):
            i2 = i + (i & -i)
            if i2 < self.size:
                self.array[i2] += self.array[i]

    def prefix_query(self, i):
        i += 1
        result = 0
        while i:
            result += self.array[i]
            i -= i & -i
        return result

    def range_query(self, from_i, to_i):
        return self.prefix_query(to_i) - self.prefix_query(from_i - 1)

    def update(self, i, add):
        i += 1
        while i < self.size:
            self.array[i] += add
            i += i & -i
