import pandas as pd
import joblib

rf_loaded = joblib.load('model/random_forest.joblib')
le_loaded = joblib.load('model/label_encoder.joblib')

new_data = pd.DataFrame({
    'Pclass': [3],
    'Sex': ['male'], 
    'Age': [22],
    'SibSp': [1],
    'Parch': [0],
    'Fare': [7.25],
    'Title_Mr': [1] 
})

new_data['Sex'] = le_loaded.transform(new_data['Sex'])

prediction = rf_loaded.predict(new_data)
survival_status = 'Survived' if prediction[0] == 1 else 'Did not survive'
print(f'The prediction for the new data is: {survival_status}')