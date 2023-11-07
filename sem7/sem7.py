import random

class Stack:
    def __init__(self, capacity):
        self.stack = []
        self.capacity = capacity
        self.top = -1

    def isEmpty(self):
        return self.top == -1

    def peek(self):
        if not self.isEmpty():
            return self.stack[self.top]
        else:
            return None

    def push(self, newElement):
        if self.top < self.capacity - 1:
            self.stack.append(newElement)
            self.top += 1

    def pop(self):
        if not self.isEmpty():
            popped_element = self.stack.pop()
            self.top -= 1
            return popped_element
        else:
            return None

    def size(self):
        return self.top + 1

    def exists(self, element):
        return element in self.stack

    def unique(self):
        unique_stack = Stack(self.capacity)
        seen_elements = set()

        for element in self.stack:
            if element not in seen_elements:
                unique_stack.push(element)
                seen_elements.add(element)

        return unique_stack

    def toArray(self):
        return self.stack

    def rand(self):
        random.shuffle(self.stack)
        return self

def main():
    capacity = int(input("Stack-iin hemjeeg oruulah: "))
    stack = Stack(capacity)

    while True:
        print("\nStack-d hiigdeh uildeluud:")
        print("1. Push")
        print("2. Pop")
        print("3. Peek")
        print("4. Size")
        print("5. Check if Element Exists")
        print("6. Remove Duplicates")
        print("7. Convert to Array")
        print("8. Shuffle (Randomize) Stack")
        print("9. Quit")

        choice = int(input("Songolto oruulna u: "))

        if choice == 1:
            element = input("Enter the element to push: ")
            stack.push(element)
        elif choice == 2:
            popped_element = stack.pop()
            if popped_element is not None:
                print(f"Popped Element: {popped_element}")
            else:
                print("Stack is empty.")
        elif choice == 3:
            top_element = stack.peek()
            if top_element is not None:
                print(f"Top Element: {top_element}")
            else:
                print("Stack is empty.")
        elif choice == 4:
            print(f"Stack Size: {stack.size()}")
        elif choice == 5:
            element = input("Enter the element to check: ")
            if stack.exists(element):
                print("Element exists in the stack.")
            else:
                print("Element does not exist in the stack.")
        elif choice == 6:
            unique_stack = stack.unique()
            print("Stack with duplicates removed:")
            while not unique_stack.isEmpty():
                print(unique_stack.pop())
        elif choice == 7:
            elements_array = stack.toArray()
            print("Stack Elements as Array:", elements_array)
        elif choice == 8:
            shuffled_stack = stack.rand()
            print("Shuffled Stack Elements:", shuffled_stack.toArray())
        elif choice == 9:
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
