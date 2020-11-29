select C.CarMaker,C.CarModel, sum(C.CarMaker,C.CarModel)
from carSales C
group by C.CarMaker,C.CarModel
having C.saleSate < DATEADD(day, -30, GETDATE());
