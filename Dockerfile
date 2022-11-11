FROM python

WORKDIR /home
COPY main.py .

RUN pip install fastapi \
   && pip install "uvicorn[standard]"

ENTRYPOINT [ "uvicorn","main:app","--reload", "--port", "8080", "--host", "0.0.0.0"]