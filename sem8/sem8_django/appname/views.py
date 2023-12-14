from django.shortcuts import render
from django.views.generic import TemplateView

class YourTemplateView(TemplateView):
    template_name = 'yourappname/index.html'
# Create your views here.
