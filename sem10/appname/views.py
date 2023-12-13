from django.shortcuts import render
import joblib

# Load the model at the beginning, so it gets loaded into memory only once
model = joblib.load('model/model.joblib')

def index(request):
    return render(request, 'index.html')

def result(request):
    # Get input values from the form in index.html
    # For example, if you had an input field named 'age':
    # age = request.POST.get('age')
    
    # Prepare the input data in the form expected by the model
    # and make a prediction
    # prediction = model.predict([[age, ...]])

    # Pass the prediction to the template
    return render(request, 'result.html', {'prediction': prediction})