class Worker:
    def __init__(self, worker_code, worker_name, fixed_salary, social_security_rate, health_insurance_rate):
        self.worker_code = worker_code
        self.worker_name = worker_name
        self.fixed_salary = fixed_salary
        self.social_security_rate = social_security_rate
        self.health_insurance_rate = health_insurance_rate

    def calculateSalary(self):
        total_deductions = self.fixed_salary * (self.social_security_rate + self.health_insurance_rate)
        return self.fixed_salary - total_deductions

    def calculateTax(self):
        return self.fixed_salary * (self.social_security_rate + self.health_insurance_rate)

    def __str__(self):
        return f"Worker Code: {self.worker_code}, Worker Name: {self.worker_name}, Fixed Salary: {self.fixed_salary}"

class BasicWorker(Worker):
    def __init__(self, worker_code, worker_name, fixed_salary, social_security_rate, health_insurance_rate, bonus_salary=100000):
        super().__init__(worker_code, worker_name, fixed_salary, social_security_rate, health_insurance_rate)
        self.bonus_salary = bonus_salary

    def calculateSalary(self):
        total_deductions = (self.fixed_salary + self.bonus_salary) * (self.social_security_rate + self.health_insurance_rate)
        return (self.fixed_salary + self.bonus_salary) - total_deductions

class Company:
    def __init__(self):
        self.resources = []

    def countWorkers(self):
        return len(self.resources)

    def addNewWorker(self, worker):
        if isinstance(worker, BasicWorker):
            self.resources.append(worker)

    def calculateTotalTax(self):
        total_tax = 0
        for worker in self.resources:
            total_tax += worker.calculateTax()
        return total_tax

    def __str__(self):
        return f"Total Employees: {self.countWorkers()}, Total Tax: {self.calculateTotalTax()}"

workers = []
workers.append(Worker(1, "Worker 1", 50000, 0.1, 0.05))
workers.append(Worker(2, "Worker 2", 60000, 0.12, 0.06))
workers.append(Worker(3, "Worker 3", 70000, 0.15, 0.07))
workers.append(Worker(4, "Worker 4", 55000, 0.11, 0.05))
workers.append(Worker(5, "Worker 5", 65000, 0.13, 0.06))

basic_workers = []
basic_workers.append(BasicWorker(6, "BasicWorker 1", 75000, 0.16, 0.08, 200000))
basic_workers.append(BasicWorker(7, "BasicWorker 2", 80000, 0.18, 0.09, 150000))

company = Company()
for worker in workers:
    company.addNewWorker(worker)
for basic_worker in basic_workers:
    company.addNewWorker(basic_worker)

for worker in company.resources:
    print(f"{worker.worker_name}: Salary - {worker.calculateSalary()}, Tax - {worker.calculateTax()}")

print(company)