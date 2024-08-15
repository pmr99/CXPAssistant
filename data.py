from datetime import datetime
import json

class WorkerData:
    def __init__(self):
        self.data = mutable_Ui_content
        self.schema = mutable_Ui_content_schema

    def getData(self):
        self.data["currentTime"] = str(datetime.now())
        return self.data

    def getSchema(self):
        return self.schema



mutable_Ui_content = {
    "currentTime": "",
    "notCheckedInAttendanceGroup": [
        {
            "id": 1,
            "title": "Financial Services - Cashier",
            "emptyStatus": "Everyone Checked In",
            "timeZoneID": "",
            "workers": [
                {
                "workerId": 1,
                "shiftId": 1,
                "name": "Rosa Chavez",
                "shiftStartTime": "8:00am",
                "shiftEndTime": "3:00pm",
                "role": "SF Store - Cashier",
                "status": "15MinutesLate",
                "phoneNumber": "+5103948193",
                },
                {
                "workerId": 2,
                "shiftId": 2,
                "name": "Maria Aguilar",
                "shiftStartTime": "9:00am",
                "shiftEndTime": "3:00pm",
                "role": "SF Store - Cashier",
                "status": "LateLessThan15MinutesLate",
                "phoneNumber": "+3184929493",
                },
                {
                "workerId": 3,
                "shiftId": 3,
                "name": "Tara Fontana",
                "shiftStartTime": "8:00am",
                "shiftEndTime": "6:00pm",
                "role": "SF Store - Cashier",
                "status": "MoreThan30MinutesLate",
                "phoneNumber": "+5920583829",
                },
            ],
        },
        {
            "id": 2,
            "title": "Financial Services - Sales Associate",
            "emptyStatus": "null",
            "timeZoneID": "",
            "workers": [
                {
                    "workerId": 1,
                    "shiftId": 1,
                    "name": "Frank Roche",
                    "shiftStartTime": "8:00am",
                    "shiftEndTime": "4:00pm",
                    "role": "SF Store - Sales",
                    "status": "LateLessThan15Minutes",
                    "phoneNumber": "+5103942848",
                },
                {
                    "workerId": 2,
                    "shiftId": 2,
                    "name": "Paul Ricci",
                    "shiftStartTime": "9:00am",
                    "shiftEndTime": "3:00pm",
                    "role": "SF Store - Sales",
                    "status": "LateMoreThan30Minutes",
                    "phoneNumber": "+4231930402",
                },
            ],
        },
    ],
    "notCheckedOutAttendanceGroup": [
        {
            "id": 1,
            "title": "Financial Services - Cashier",
            "emptyStatus": "Everyone Checked In",
            "timeZoneID": "",
            "workers": [
            ],
        },
        {
            "id": 2,
            "title": "Financial Services - Sales Associate",
            "emptyStatus": "null",
            "timeZoneID": "",
            "workers": [
                {
                    "workerId": 1,
                    "shiftId": 1,
                    "name": "Christopher Bannon",
                    "description": "",
                    "status": "LateLessThan15Minutes",
                    "phoneNumber": "+5104929949",
                },
                {
                    "workerId": 2,
                    "shiftId": 2,
                    "name": "Michael Turner",
                    "description": "",
                    "status": "LateMoreThan30Minutes",
                    "phoneNumber": "+5104829495",
                },
            ],
        },
    ],
}

mutable_Ui_content_schema = {
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "description": "Schema for data of workers who have not checked in and not checked out for their respective shifts at different times and groups.",
  "properties": {
    "currentTime": {
      "type": "string",
      "description": "Current time"
    },
    "notCheckedInAttendanceGroup": {
      "type": "array",
      "description": "Array of attendance groups of the workers who have not checked in",
      "items": {
        "type": "object",
        "description": "Details of an attendance group",
        "properties": {
          "id": {
            "type": "integer",
            "description": "Unique identifier of the attendance group"
          },
          "title": {
            "type": "string",
            "description": "Title of the attendance group, also known as Sub Group Organization (SGO)"
          },
          "emptyStatus": {
            "type": "string",
            "description": "Status indicating if everyone has checked in. If there are workers in this list, this will be null"
          },
          "timeZoneID": {
            "type": "string",
            "description": "Time zone ID of the attendance group"
          },
          "workers": {
            "type": "array",
            "description": "Array of workers in the attendance group",
            "items": {
              "type": "object",
              "description": "Details of a worker",
              "properties": {
                "workerId": {
                  "type": "integer",
                  "description": "Unique identifier of the worker"
                },
                "shiftId": {
                  "type": "integer",
                  "description": "Unique identifier of the shift"
                },
                "name": {
                  "type": "string",
                  "description": "Name of the worker"
                },
                "shiftStartTime": {
                  "type": "string",
                  "description": "Start time of the shift"
                },
                "shiftEndTime": {
                  "type": "string",
                  "description": "End time of the shift"
                },
                "role": {
                  "type": "string",
                  "description": "Role of the worker"
                },
                "status": {
                  "type": "string",
                  "description": "Late status of the worker: can either be null, LateLessThan15Min, Late15min or LateMoreThan30min"
                },
                "phoneNumber": {
                  "type": "string",
                  "description": "worker's Phone Number"
                }
              },
              "required": ["workerId", "shiftId", "name", "shiftStartTime", "shiftEndTime", "role", "status", "phoneNumber"]
            }
          }
        },
        "required": ["id", "title", "emptyStatus", "timeZoneID", "workers"]
      }
    },
    "notCheckedOutAttendanceGroup": {
      "type": "array",
      "description": "Array of attendance groups of workers who have not checked out",
      "items": {
        "type": "object",
        "description": "Details of an attendance group",
        "properties": {
          "id": {
            "type": "integer",
            "description": "Unique identifier of the attendance group"
          },
          "title": {
            "type": "string",
            "description": "Title of the attendance group, also known as Sub Group Organization (SGO)"
          },
          "emptyStatus": {
            "type": "string",
            "description": "Status indicating if everyone has checked in. If there are workers in this list, this will be null"
          },
          "timeZoneID": {
            "type": "string",
            "description": "Time zone ID of the attendance group"
          },
          "workers": {
            "type": "array",
            "description": "Array of workers in the attendance group",
            "items": {
              "type": "object",
              "description": "Details of a worker",
              "properties": {
                "workerId": {
                  "type": "integer",
                  "description": "Unique identifier of the worker"
                },
                "shiftId": {
                  "type": "integer",
                  "description": "Unique identifier of the shift"
                },
                "name": {
                  "type": "string",
                  "description": "Name of the worker"
                },
                "shiftStartTime": {
                  "type": "string",
                  "description": "Start time of the shift"
                },
                "shiftEndTime": {
                  "type": "string",
                  "description": "End time of the shift"
                },
                "role": {
                  "type": "string",
                  "description": "Role of the worker"
                },
                "status": {
                  "type": "string",
                  "description": "Late status of the worker: can either be null, LateLessThan15Min, Late15min or LateMoreThan30min"
                },
                "phoneNumber": {
                  "type": "string",
                  "description": "Phone number of the worker"
                }
              },
              "required": ["workerId", "shiftId", "name", "shiftStartTime", "shiftEndTime", "role", "status", "phoneNumber"]
            }
          }
        },
        "required": ["id", "title", "emptyStatus", "timeZoneID", "workers"]
      }
    }
  },
  "required": ["currentTime", "notCheckedInAttendanceGroup", "notCheckedOutAttendanceGroup"]
}
