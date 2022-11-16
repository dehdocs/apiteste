from fastapi import FastAPI
from kafka import KafkaProducer
import os

app = FastAPI()
kafka_server = os.getenv('KAFKA_SERVER')
kafka_port = os.getenv('KAFKA_PORT')
kafka_url = f'{kafka_server}:{kafka_port}'

producer = KafkaProducer(bootstrap_servers=[kafka_url])

@app.get("/")
async def root():
    producer.send('Api', b'teste de mensagem')
    return {"number":'mensagem'}
