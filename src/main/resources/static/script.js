function getMonthHeatMap(vehicleId)
{
    let div = document.createElement("div")
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
           cnt++

           /*if (cnt%7 == 0)
               div.appendChild(document.createElement(""))*/

           //div.appendChild(p)
       }
    }
    return div

}