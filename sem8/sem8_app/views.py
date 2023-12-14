from django.shortcuts import render

def index(request):
    return render(request, 'sem8_app/index.html')