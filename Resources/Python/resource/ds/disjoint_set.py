class DisjointSet:
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.size = [1] * n

    def find(self, i):
        if self.parent[i] == i:
            return i
        else:
            self.parent[i] = self.find(self.parent[i])
            return self.parent[i]

    def union(self, a, b):
        x, y = self.find(a), self.find(b)
        if x != y:
            self.size[y] += self.size[x]
            self.parent[x] = self.parent[b]

    def get_size(self, i):
        return self.size[self.find(i)]
