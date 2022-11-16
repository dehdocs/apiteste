from fastapi import FastAPI
from kafka import KafkaProducer
import os, random

app = FastAPI()
kafka_server = os.getenv('KAFKA_SERVER')
kafka_port = os.getenv('KAFKA_PORT')
kafka_url = f'{kafka_server}:{kafka_port}'

producer = KafkaProducer(bootstrap_servers=[kafka_url])

@app.get("/")
async def root():
    number = random.randint(0,9999999)
    producer.send('Api', number)
    return {"number": number}
