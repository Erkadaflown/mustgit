import os
import joblib
from django.conf import settings

class MyModelLoader:
    @staticmethod
    def get_model():
        model_path = os.path.join(settings.BASE_DIR, 'predictor', 'ml_models', 'random_forest.joblib')
        return joblib.load(model_path)

    @staticmethod
    def get_label_encoder():
        encoder_path = os.path.join(settings.BASE_DIR, 'predictor', 'ml_models', 'label_encoder.joblib')
        return joblib.load(encoder_path)
