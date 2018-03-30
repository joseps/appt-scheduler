# Appointments Scheduler
    A system to schedule and manage doctor's appointments
    Provides a robust API for 3rd party vendors to interoperate with
    Used to create appointments, update appointments and search existing appointments
    Systems can subscribe for notifications when appointments are created or updated

# Create/Update Request
    {
        "id" : 1,
        "startDateTime" : "2018-04-01T14:00:00.000Z",
        "duration" : 45,
        "patientId" : 1000000002,
        "providerId" : 2000000001,
        "departmentId" : 501
    }

# Search Request
    {
        "fromDate" : "2018-03-01T00:00:00.000Z",
        "toDate" : "2018-04-30T23:00:00.000Z",
    }