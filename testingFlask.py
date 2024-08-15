
from flask import Flask, jsonify, request
import os
from xml.dom.minidom import Document
from llama_index.core import VectorStoreIndex, Document
from data import WorkerData
from llama_index.llms.openai import OpenAI
from llama_index.llms.gemini import Gemini
from llama_index.core import ServiceContext, set_global_service_context



os.environ["OPENAI_API_KEY"] = "APIKEY"
os.environ["GOOGLE_API_KEY"] = "APIKEY"


# Flask Constructor
app = Flask(__name__)


workerData = WorkerData()
#
#
def removeWorker(name):
    info = workerData.getData()
    for notCheckedIn in info['notCheckedInAttendanceGroup']:
        for index, worker in enumerate(notCheckedIn['workers']):
            if worker['name'] == name:
                notCheckedIn['workers'].pop(index)
                return True
    return False




# decorator to associate
# a function with the url
@app.route("/")
def showHomePage():
    # response from the server
    #llm = Gemini(model="models/gemini-1.5-pro", temperature=0, max_tokens=256)
    llm = OpenAI(model = "gpt-3.5-turbo", temperature=0, max_tokens=256)
    result = "".join([str(workerData.getData()), str(workerData.getSchema())])
    doc = Document(text =result)
    service_context = ServiceContext.from_defaults(llm=llm, chunk_size=800, chunk_overlap=20)
    index = VectorStoreIndex.from_documents([doc], service_context=service_context)
    query_engine = index.as_query_engine()
    response = query_engine.query("How was the weather in San Franscisco when the first comment was posted?")
    return str(response)


@app.route('/post', methods=['POST'])
def handle_post():
    data = request.get_json()
    if data.get('remove')!="":
        response = removeWorker(data.get('remove'))
        if response:
            return "Success!"
        else:
            return "Could not find this worker in the list of workers not checked in."
    name = data.get('message')
    llm = Gemini(model="models/gemini-1.5-pro", temperature=0, max_tokens=256)
    # llm = OpenAI(model = "gpt-3.5-turbo", temperature=0, max_tokens=256)
    result = "".join([str(workerData.getData()), str(workerData.getSchema())])
    doc = Document(text =result)
    service_context = ServiceContext.from_defaults(llm=llm, chunk_size=800, chunk_overlap=20)
    index = VectorStoreIndex.from_documents([doc], service_context=service_context)
    query_engine = index.as_query_engine()
    response = query_engine.query(name)
    return str(response)




if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000, debug=True)
