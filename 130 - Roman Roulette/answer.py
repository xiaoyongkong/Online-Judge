#!/usr/bin/env python3
"""UVa 130 - Roman Roulette."""

import sys


def winning_start(n: int, step: int) -> int:
    """Return the 1-based initial position that leaves person 1 alive.

    The simulation follows the statement's kill-and-bury process.  Once its
    survivor is known, rotating the initial circle puts that survivor at 1.
    """
    people = list(range(1, n + 1))
    kill = (step - 1) % n
    bury = (kill + step) % n

    # Keep the original circle positions: after a burial the next victim is
    # found by skipping positions already marked as removed.
    for _ in range(n - 2):
        people[kill] = people[bury]
        people[bury] = 0

        for _ in range(step):
            kill = (kill + 1) % n
            while people[kill] == 0:
                kill = (kill + 1) % n
        people[kill] = 0

        for _ in range(step):
            bury = (bury + 1) % n
            while people[bury] == 0:
                bury = (bury + 1) % n

    survivor = next(person for person in people if person) - 1
    return (n - survivor) % n + 1


def solve(data: str) -> str:
    values = list(map(int, data.split()))
    answer = []
    for n, step in zip(values[::2], values[1::2]):
        if n == step == 0:
            break
        answer.append(str(winning_start(n, step)))
    return "\n".join(answer)


if __name__ == "__main__":
    print(solve(sys.stdin.read()))
