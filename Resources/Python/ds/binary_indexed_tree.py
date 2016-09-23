class BinaryIndexedTree:
    def __init__(self, array):
        """"Initialize BIT with list in O(n)"""
        self.array = [0] + array
        for i in range(1, len(self.array)):
            i2 = i + (i & -i)
            if i2 < len(self.array):
                self.array[i2] += self.array[i]

    def prefix_query(self, i):
        """Computes prefix sum of up to including the i-th element"""
        i += 1
        result = 0
        while i:
            result += self.array[i]
            i -= i & -i
        return result

    def range_query(self, from_i, to_i):
        """Computes the range sum between two indices (both inclusive)"""
        return self.prefix_query(to_i) - self.prefix_query(from_i - 1)

    def update(self, i, add):
        """Add a value to the idx-th element"""
        i += 1
        while i < len(self.array):
            self.array[i] += add
            i += i & -i


if __name__ == '__main__':
    arr = [1, 7, 3, 0, 5, 8, 3, 2, 6, 2, 1, 1, 4, 5]
    bit = BinaryIndexedTree(arr)
    print('Array: [{}]'.format(', '.join(map(str, arr))))
    print()

    print('Prefix sum of first {} elements: {}'.format(13, bit.prefix_query(12)))
    print('Prefix sum of first {} elements: {}'.format(7, bit.prefix_query(6)))
    print('Range sum from pos {} to pos {}: {}'.format(1, 5, bit.range_query(1, 5)))
    print()

    bit.update(4, 2)
    print('Add {} to element at pos {}'.format(2, 4))
    new_array = [bit.range_query(idx, idx) for idx in range(len(arr))]
    print('Array: [{}]'.format(', '.join(map(str, new_array))))
    print()

    print('Prefix sum of first {} elements: {}'.format(13, bit.prefix_query(12)))
    print('Prefix sum of first {} elements: {}'.format(7, bit.prefix_query(6)))
    print('Range sum from pos {} to pos {}: {}'.format(1, 5, bit.range_query(1, 5)))
    print()
