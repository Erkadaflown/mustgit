def find_most_repeated_characters(company_name):
    
    if len(company_name) <= 3 or len(set(company_name)) < 3:
        return "Buruu utga oruulsan bn ugugdulu ushalgana u."

    char_count = {}
    for char in company_name:
        char_count[char] = char_count.get(char, 0) + 1

    sorted_chars = sorted(char_count.keys(), key=lambda char: (-char_count[char], char))

    top_three_chars = sorted_chars[:3]
    top_three_counts = [char_count[char] for char in top_three_chars]

    for char, count in zip(top_three_chars, top_three_counts):
        print(f"{char} {count:02}")

company_name = input("Company neriig oruulna u (zuvhun jijig useg): ").strip()

result = find_most_repeated_characters(company_name)
print(result)
