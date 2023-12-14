from django.shortcuts import render
from .ml_models import MyModelLoader
import pandas as pd

def input_view(request):
    # Simply render the input form page
    return render(request, 'input.html')

def predict_view(request):
    prediction = None  

    if request.method == 'POST':

        pclass = request.POST.get('pclass')
        sex = request.POST.get('sex') 
        age = request.POST.get('age')
        sibsp = request.POST.get('sibsp')
        parch = request.POST.get('parch')
        fare = request.POST.get('fare')
        title_mr = request.POST.get('title_mr')  

        input_data = pd.DataFrame({
            'Pclass': [pclass],
            'Sex': [sex],
            'Age': [age],
            'SibSp': [sibsp],
            'Parch': [parch],
            'Fare': [fare],
            'Title_Mr': [title_mr]
        })

        model = MyModelLoader.get_model()
        le = MyModelLoader.get_label_encoder()

        input_data['Sex'] = le.transform(input_data['Sex'])

        model_prediction = model.predict(input_data)
        prediction = 'Амьд үлдсэн' if model_prediction[0] == 1 else 'Амьд үлдээгүй'

    context = {'prediction': prediction}
    return render(request, 'result.html', context)
