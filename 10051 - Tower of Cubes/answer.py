#!/usr/bin/env python3
"""UVa 10051 - Tower of Cubes."""

import sys


FACES = ("front", "back", "left", "right", "top", "bottom")
OPPOSITE = (1, 0, 3, 2, 5, 4)


def solve(lines: list[str]) -> str:
    pos = 0
    case = 1
    output: list[str] = []

    while pos < len(lines):
        n = int(lines[pos])
        pos += 1
        if n == 0:
            break

        # best[color] is the tallest tower whose exposed upper face has color.
        # Each entry also stores the last oriented cube in that tower.
        best: dict[int, tuple[int, int]] = {}
        parent: dict[tuple[int, int], tuple[int, int] | None] = {}
        heights: dict[tuple[int, int], int] = {}

        for cube_id in range(1, n + 1):
            colors = list(map(int, lines[pos].split()))
            pos += 1
            updates: list[tuple[int, int, tuple[int, int] | None, int]] = []

            for face in range(6):
                bottom_color = colors[OPPOSITE[face]]
                previous = best.get(bottom_color)
                height = (previous[0] if previous else 0) + 1
                updates.append((colors[face], height, previous[1] if previous else None, face))

            # A cube cannot be used as both the previous and current cube.
            for top_color, height, previous, face in updates:
                node = (cube_id, face)
                heights[node] = height
                parent[node] = previous
                if top_color not in best or height > best[top_color][0]:
                    best[top_color] = (height, node)

        _, last = max(best.values(), key=lambda entry: entry[0])
        tower: list[tuple[int, int]] = []
        while last is not None:
            tower.append(last)
            last = parent[last]
        tower.reverse()

        if case > 1:
            output.append("")
        output.extend((f"Case #{case}", str(len(tower))))
        output.extend(f"{cube_id} {FACES[face]}" for cube_id, face in tower)
        case += 1

    return "\n".join(output)


if __name__ == "__main__":
    print(solve(sys.stdin.read().splitlines()))
