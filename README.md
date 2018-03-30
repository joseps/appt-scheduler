# Appointments Scheduler
    Appointments Scheduler is a prototypical system which provides an API to create, update and track
    patient appointments with their providers. This API can be used by 3rd party vendors to create
    appointments, update appointments and search existing appointments. It provides a feature to
    notify subscribers when appointments are created or updated.

# Create Request
    URI: /appointments/create
    Sample request:
    {
        "startDateTime" : "2018-04-01T14:00:00.000Z",
        "duration" : 30,
        "patientId" : 1000000002,
        "providerId" : 2000000001,
        "departmentId" : 501
    }

# Update Request
    URI: /appointments/update
    Sample request:
    {
        "id" : 1234,
        "startDateTime" : "2018-05-01T14:00:00.000Z",
        "duration" : 30,
        "patientId" : 1000000002,
        "providerId" : 200000001,
        "departmentId" : 501
    }

# Search Request
    URI: /appointments/search
    Sample request:
    {
        "fromDate" : "2018-04-01T00:00:00.000Z",
        "toDate" : "2018-04-30T23:00:00.000Z",
    }

# Technologies Used
    Java 1.8
    MySQL 5.7
    Spring Boot