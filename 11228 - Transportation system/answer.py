#!/usr/bin/env python3
"""UVa 11228 - Transportation system."""

import math
import sys


class DisjointSet:
    def __init__(self, size: int) -> None:
        self.parent = list(range(size))
        self.rank = [0] * size

    def find(self, node: int) -> int:
        while self.parent[node] != node:
            self.parent[node] = self.parent[self.parent[node]]
            node = self.parent[node]
        return node

    def union(self, left: int, right: int) -> bool:
        left, right = self.find(left), self.find(right)
        if left == right:
            return False
        if self.rank[left] < self.rank[right]:
            left, right = right, left
        self.parent[right] = left
        if self.rank[left] == self.rank[right]:
            self.rank[left] += 1
        return True


def solve(data: str) -> str:
    values = iter(map(int, data.split()))
    cases = next(values)
    output: list[str] = []

    for case in range(1, cases + 1):
        n, radius = next(values), next(values)
        points = [(next(values), next(values)) for _ in range(n)]
        edges: list[tuple[float, int, int]] = []
        for i in range(n):
            for j in range(i + 1, n):
                distance = math.hypot(points[i][0] - points[j][0], points[i][1] - points[j][1])
                edges.append((distance, i, j))
        edges.sort()

        dsu = DisjointSet(n)
        states, roads, rails = 1, 0.0, 0.0
        for distance, left, right in edges:
            if not dsu.union(left, right):
                continue
            if distance <= radius:
                roads += distance
            else:
                rails += distance
                states += 1

        output.append(f"Case #{case}: {states} {int(roads + 0.5)} {int(rails + 0.5)}")
    return "\n".join(output)


if __name__ == "__main__":
    print(solve(sys.stdin.read()))
