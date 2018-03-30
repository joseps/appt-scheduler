# Appointments Scheduler
    Appointments Scheduler is a prototypical system which provides an API to create, update and track
    patient appointments with their providers. This API can be used by 3rd party vendors to create
    appointments, update appointments and search existing appointments. It provides a feature to
    notify subscribers when appointments are created or updated.

# Sample Create/Update Request
    {
        "id" : 1,
        "startDateTime" : "2018-04-01T14:00:00.000Z",
        "duration" : 30,
        "patientId" : 1000000002,
        "providerId" : 2000000001,
        "departmentId" : 501
    }

# Sample Search Request
    {
        "fromDate" : "2018-03-01T00:00:00.000Z",
        "toDate" : "2018-04-30T23:00:00.000Z",
    }