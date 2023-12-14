from django.urls import path
from . import views

urlpatterns = [
    path('input/', views.input_view, name='input'),
    path('predict/', views.predict_view, name='predict'),
]
