def count_possible_vaccines(coronavirus_representation):

    digit_count = {}

    possible_vaccines = 0

    for digit in coronavirus_representation:
        if digit in digit_count:
            digit_count[digit] += 1
        else:
            digit_count[digit] = 1

    for count in digit_count.values():
        possible_vaccines += count * (count - 1) // 2

    return possible_vaccines

coronavirus_representation = input("Corono virusiin decimal utgiig oruulna u: ")

possible_vaccines_count = count_possible_vaccines(coronavirus_representation)
print("Vaccatsinii too:", possible_vaccines_count)
