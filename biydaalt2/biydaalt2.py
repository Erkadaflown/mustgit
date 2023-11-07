class Stack:
    def __init__(self, initial_capacity):
        self.stack = []
        self.top = -1
        self.initial_capacity = initial_capacity

    def isEmpty(self):
        return self.top == -1

    def peek(self):
        if not self.isEmpty():
            return self.stack[self.top]
        else:
            return None

    def push(self, new_element):
        if self.top < self.initial_capacity - 1:
            self.stack.append(new_element)
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
        unique_stack = Stack(self.initial_capacity)
        seen_elements = set()

        for element in self.stack:
            if element not in seen_elements:
                unique_stack.push(element)
                seen_elements.add(element)

        return unique_stack

    def rand(self):
        shuffled_stack = Stack(self.initial_capacity)
        shuffled_elements = self.stack[:]
        random.shuffle(shuffled_elements)
        for element in shuffled_elements:
            shuffled_stack.push(element)
        return shuffled_stack

class Car:
    def __init__(self, chiglel, dugaar):
        self.chiglel = chiglel
        self.dugaar = dugaar

    def get_chiglel(self):
        return self.chiglel

    def get_dugaar(self):
        return self.dugaar

class CarParking:
    def __init__(self, initial_capacity):
        self.zogsool = Stack(initial_capacity)

    def input(self, car):
        if self.zogsool.size() < self.zogsool.initial_capacity:
            self.zogsool.push(car)
            return f"The car with number {car.get_dugaar()} stopped at the parking lot."
        else:
            return f"The car with number {car.get_dugaar()} will not be allowed to park. The parking lot is full."

    def process(self, car):
        num_cars_behind = 0
        found = False

        while not self.zogsool.isEmpty():
            num_cars_behind += 1
            current_car = self.zogsool.pop()
            if current_car.get_dugaar() == car.get_dugaar():
                found = True
                return f"The car with number {car.get_dugaar()} left the parking lot after moving {num_cars_behind - 1} cars."

        if not found:
            return f"There is no car with exit number {car.get_dugaar()} in the parking lot."

def main():
    capacity = 10 
    car_parking = CarParking(capacity)

    with open("cars.txt", 'r') as file:
        for line in file:
            operation, car_data = line.strip().split()
            chiglel, dugaar = car_data.split('-')
            car = Car(chiglel, dugaar)

            if operation == 'A':
                result = car_parking.input(car)
                print(result)
            elif operation == 'D':
                result = car_parking.process(car)
                print(result)

if __name__ == "__main__":
    main()
