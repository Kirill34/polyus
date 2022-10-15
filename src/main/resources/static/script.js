function getMonthHeatMap(vehicleId)
{
    let div = document.createElement("div")

    let byHourBlock = document.createElement("div")
    div.appendChild(byHourBlock)

    let xhr = new XMLHttpRequest()
    xhr.open("GET","../../request/forVehicle/"+vehicleId+"/byDayLoad")
    xhr.responseType="json"
    xhr.send()
    xhr.onload = () => {
       let dates = xhr.response
        console.log(dates)
        let cnt = 0
        let rows = []
        let currentRow;
       for (let date in dates)
       {

           if (cnt%7 == 0)
           {
               currentRow = document.createElement("div")
               currentRow.classList.add("row")
               div.appendChild(currentRow)
           }
           console.log(date)
           let p = document.createElement("p")
           p.innerText = date
           p.style.backgroundColor = "#ffffff"
           p.style.opacity = "50%"


           let cell = document.createElement("div")

           if (dates[date]<1)
               cell.style.backgroundColor  = "#024702"

           if (dates[date]>=1 && dates[date]<=5)
               cell.style.backgroundColor  = "#4d7204"

           if (dates[date]>5 && dates[date]<10)
                cell.style.backgroundColor  = "#a17d06"

           if (dates[date]>=10 && dates[date]<=16)
           cell.style.backgroundColor  = "#a17d06"

           if (dates[date]>16 && dates[date]<=21)
            cell.style.backgroundColor  = "#643704"

           if (dates[date]>21)
               cell.style.backgroundColor  = "#3b0902"



           cell.style.width = "100px"
           cell.style.height = "100px"
           cell.style.float="left"
           cell.appendChild(p)

           currentRow.appendChild(cell)

           cell.onclick = () => {
               let byHour = getDayHeatMap(vehicleId,date)
               byHourBlock.appendChild(byHour)
           }

           cnt++

           /*if (cnt%7 == 0)
               div.appendChild(document.createElement(""))*/

           //div.appendChild(p)
       }
    }
    return div

}


function getDayHeatMap(vehicleId,date)
{
    let div = document.createElement("div")
    div.style.margin="10px"
    let pText = document.createElement("p")
    pText.innerText = "Загруженность по часам на дату: "+date
    div.appendChild(pText)
    let xhr = new XMLHttpRequest()
    xhr.open("GET","../../request/forVehicle/"+vehicleId+"/byHourLoad?date="+date)
    xhr.responseType="json"
    xhr.send()
    xhr.onload = () => {
        let dates = xhr.response
        console.log(dates)
        let cnt = 0
        let rows = []
        let currentRow;
        for (let date in dates)
        {

            if (cnt%24 == 0)
            {
                currentRow = document.createElement("div")
                currentRow.classList.add("row")
                div.appendChild(currentRow)
            }
            console.log(date)
            let p = document.createElement("p")
            p.innerText = date
            p.style.backgroundColor = "#ffffff"
            p.style.opacity = "50%"


            let cell = document.createElement("div")

            if (dates[date]<5)
                cell.style.backgroundColor  = "#024702"

            if (dates[date]>=5 && dates[date]<=15)
                cell.style.backgroundColor  = "#4d7204"

            if (dates[date]>15 && dates[date]<25)
                cell.style.backgroundColor  = "#a17d06"

            if (dates[date]>=25 && dates[date]<=35)
                cell.style.backgroundColor  = "#a17d06"

            if (dates[date]>35 && dates[date]<=45)
                cell.style.backgroundColor  = "#643704"

            if (dates[date]>45)
                cell.style.backgroundColor  = "#3b0902"



            cell.style.width = "60px"
            cell.style.height = "30px"
            cell.style.float="left"
            cell.appendChild(p)
            cell.style.margin = "5px"
            cell.style.padding = "5px"
            cell.style.borderColor = "#000000"

            currentRow.appendChild(cell)
            cnt++

            /*if (cnt%7 == 0)
                div.appendChild(document.createElement(""))*/

            //div.appendChild(p)
        }
    }

    getRequestsForDay(vehicleId, date)

    return div

}


function getRequestsForDay(vehicleId, date)
{
    let xhr = new XMLHttpRequest()
    xhr.open("GET","../../request/forVehicle/"+vehicleId+"/forDay?date="+date)
    xhr.responseType="json"
    xhr.send()
    xhr.onload = () =>
    {
        let text = ""
        let reqs = (xhr.response)
        for (let key in reqs)
        {
            text+="Время: c " + reqs[key].startDateTime + " по " + reqs[key].finishDateTime + "\n"
            text+="Заказчик: " + reqs[key].customer.firstName + " " + reqs[key].customer.secondName + "\n"
            text+="Телефон: "+reqs[key].customer.phoneNumber
            text+="\n\n\n"
        }
        alert(text)
    }
}