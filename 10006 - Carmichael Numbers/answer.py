#!/usr/bin/env python3
"""UVa 10006 - Carmichael Numbers."""

import sys


def sieve(limit: int) -> list[bool]:
    prime = [True] * (limit + 1)
    if limit >= 0:
        prime[0] = False
    if limit >= 1:
        prime[1] = False
    for number in range(2, int(limit**0.5) + 1):
        if prime[number]:
            prime[number * number : limit + 1 : number] = [False] * (((limit - number * number) // number) + 1)
    return prime


def solve(data: str) -> str:
    numbers = [int(value) for value in data.split()]
    limit = max(numbers, default=0)
    prime = sieve(limit)
    result: list[str] = []

    for n in numbers:
        if n == 0:
            break
        # A composite n is Carmichael exactly when a^n == a (mod n) for all a.
        carmichael = not prime[n] and all(pow(a, n, n) == a for a in range(2, n))
        if carmichael:
            result.append(f"The number {n} is a Carmichael number.")
        else:
            result.append(f"{n} is normal.")
    return "\n".join(result)


if __name__ == "__main__":
    print(solve(sys.stdin.read()))
