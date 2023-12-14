from django.shortcuts import render
from .ml_models import MyModelLoader
import pandas as pd

def input_view(request):
    # Simply render the input form page
    return render(request, 'input.html')

def predict_view(request):
    prediction = None  # Initialize prediction to None

    if request.method == 'POST':
        # Extract information from POST request
        pclass = request.POST.get('pclass')
        sex = request.POST.get('sex')  # This should be 'male' or 'female'
        age = request.POST.get('age')
        sibsp = request.POST.get('sibsp')
        parch = request.POST.get('parch')
        fare = request.POST.get('fare')
        title_mr = request.POST.get('title_mr')  # This can be a checkbox

        # Prepare the data for the model
        input_data = pd.DataFrame({
            'Pclass': [pclass],
            'Sex': [sex],
            'Age': [age],
            'SibSp': [sibsp],
            'Parch': [parch],
            'Fare': [fare],
            'Title_Mr': [title_mr]
        })

        # Load model and label encoder
        model = MyModelLoader.get_model()
        le = MyModelLoader.get_label_encoder()

        # Transform 'Sex' to numeric
        input_data['Sex'] = le.transform(input_data['Sex'])

        # Make a prediction
        model_prediction = model.predict(input_data)
        prediction = 'Survived' if model_prediction[0] == 1 else 'Did not survive'

    # Pass prediction to the context
    context = {'prediction': prediction}
    return render(request, 'result.html', context)
