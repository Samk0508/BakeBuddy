import React from 'react';
import { cakeLevelThree } from '../../../data/category/level three/cakeLevelThree';
import { cakeLevelTwo } from '../../../data/category/level two/cakeLevelTwo';
import { breadLevelThree } from '../../../data/category/level three/breadLevelThree';
import { breadLevelTwo } from '../../../data/category/level two/breadLevelTwo';
import { useNavigate } from 'react-router-dom';
import { Box } from '@mui/material';
import { dessertLevelTwo } from '../../../data/category/level two/dessertLavelTwo';
import { cookiesLevelTwo } from '../../../data/category/level two/cookiesLevelTwo';
import { cookiesLevelThree } from '../../../data/category/level three/cookiesLevelThree';
import { dessertLevelThree } from '../../../data/category/level three/dessertLevelThree';

const categoryTwo = {
    cake: cakeLevelTwo,
    bread: breadLevelTwo,
    dessert: dessertLevelTwo,
    cookies: cookiesLevelTwo,
};

const categoryThree = {
    cake: cakeLevelThree,
    bread: breadLevelThree,
    dessert: dessertLevelThree,
    cookies: cookiesLevelThree,
};

const CategorySheet = ({ selectedCategory, toggleDrawer, setShowSheet }) => {
    const navigate = useNavigate();

    const childCategory = (category, parentCategoryId) => {
        return category.filter((child) => child.parentCategoryId === parentCategoryId);
    };

    const handleCategoryClick = (category) => {
        if (toggleDrawer) {
            toggleDrawer(false)();
        }
        if (setShowSheet) {
            setShowSheet(false);
        }
        navigate("/products/" + category);
    };

    return (
        <Box className='bg-white shadow-lg lg:h-[500px] overflow-y-auto'>
            <div className='flex text-sm flex-wrap'>
                {categoryTwo[selectedCategory]?.map((item, index) => (
                    <div key={item.name} className={`p-8 lg:w-[20%] ${index % 2 === 0 ? "bg-slate-50" : "bg-white"}`}>
                        <p className='text-[#00927c] mb-5 font-semibold'>{item.name}</p>
                        <ul className='space-y-3'>
                            {childCategory(categoryThree[selectedCategory], item.categoryId)?.map((subItem) => (
                                <li
                                    key={subItem.name}
                                    onClick={() => handleCategoryClick(subItem.categoryId)}
                                    className='hover:text-[#00927c] cursor-pointer'
                                >
                                    {subItem.name}
                                </li>
                            ))}
                        </ul>
                    </div>
                ))}
            </div>
        </Box>
    );
};

export default CategorySheet;
