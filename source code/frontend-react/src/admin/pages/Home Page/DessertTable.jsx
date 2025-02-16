import { useAppSelector } from "../../../Redux Toolkit/Store";
import HomeCategoryTable from "./HomeCategoryTable";

function DessertTable() {
  const { homePage} = useAppSelector((store) => store);

  return (
    <>
      <HomeCategoryTable categories={homePage.homePageData?.dessertCategories}/>
    </>
  );
}


export default DessertTable