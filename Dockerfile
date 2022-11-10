FROM python

WORKDIR /home
COPY main.py .

RUN pip install fastapi \
   && pip install "uvicorn[standard]"

ENTRYPOINT [ "uvicorn","main:app","--reload" ]