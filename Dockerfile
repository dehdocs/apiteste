FROM python

WORKDIR /home
COPY main.py .

RUN pip install fastapi \
   && pip install "uvicorn[standard]" \
   && pip install kafka-python

ENTRYPOINT [ "uvicorn","main:app","--reload", "--port", "8080", "--host", "0.0.0.0"]