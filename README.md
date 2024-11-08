# Water bill Management System

## Server Functionalities:
### Device:
1. AddDevice
2. GetDevice
3. UpdateDevice

### Client:
1. AddClient
2. GetClient
3. UpdateClient

### Bill:
1. AddBill
2. GetBill
3. UpdateBill
4. PrintBill

### Report:
1. UploadReport(using CSV file)

#### Procedure To Onboard Device and unlock all funtionalities
1. Add New client for the cutomer using AddDevice api(using api/server interface).
2. Add Device with the Serial Number and clientId(genereated by server).
3. Upload the Report of Device.

**Points to be noted:**
- Before uploading Report the device must be added with the related Serial Number along with the client.
- The Report must be in CSV format.
- Without adding device, the data in CSV file cannot be updated.
- The CSV file must have Serial Number(MeterSerial).
- Uploading same data leads to overwrite the existing data(not appened).

## Build the code

### Using Docker

#### prerequisties

* Docker installed on your machine
* Docker Compose installed on your machine

#### Steps

1. Clone the repository
2. Navigate to the project directory
3. Run the following command to run the server

```console
docker compose up --build
```
