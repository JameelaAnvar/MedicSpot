import urllib.request
import re
import pandas as pd
from bs4 import BeautifulSoup
generic_medicines_names_list = []
drug_names_list = []
drug_manufacturer_list = []
drug_type = []
drug_price = []
drug_quantity = []
drug_intake_quantity = []
drug_description = []
alphabets = ['B']
generic_medicine_name_link = 'https://www.medindia.net/drug-price/index.asp?alpha=character'
try:
 for a in alphabets:
  try:
   generic_medicine_name_url = generic_medicine_name_link.replace('character', a)
   req = urllib.request.Request(generic_medicine_name_url)
   response = urllib.request.urlopen(req)
   page = response.read()
   soup = BeautifulSoup(page)
   tables = soup.findAll("table")
   response.close()
   if len(tables) >= 2 :
    for generic_medicine_name_row in tables[1].findAll("tr",{"class": None}):
     try:
      generic_medicine_name_cols = list()
      for generic_medicine_name_col in generic_medicine_name_row.findAll("td"):
        generic_medicine_name_cols.append(generic_medicine_name_col)
      if len(generic_medicine_name_cols) >= 4 and generic_medicine_name_cols[3] :
       brand_drug_link = generic_medicine_name_cols[3].find('a')
       if brand_drug_link :
        try:
         drug_names_link = 'https://www.medindia.net/drug-price/'+brand_drug_link.get('href')   
         drug_names_req = urllib.request.Request(drug_names_link)
         drug_names_response = urllib.request.urlopen(drug_names_req)
         drug_names_page = drug_names_response.read()
         drug_names_soup = BeautifulSoup(drug_names_page)
         drug_name_tables = drug_names_soup.findAll("table")
         drug_names_response.close()
         if len(drug_name_tables) >= 2 : 
          for drug_name_row in drug_name_tables[1].findAll("tr",{"class": None}) :       
           drug_name_cols = list()
           for drug_name_col in drug_name_row.findAll("td"):
            drug_name_cols.append(drug_name_col)
           if len(drug_name_cols) >= 3  and drug_name_cols[1].find('a') and len(generic_medicine_name_cols)>= 2 and generic_medicine_name_cols[1].find('a') and len(drug_name_cols) >=4 :
            print(drug_name_cols[1].find('a').get_text())
            drug_names_list.append(drug_name_cols[1].find('a').get_text())
            drug_manufacturer_list.append(drug_name_cols[2].get_text())
            generic_medicines_names_list.append(generic_medicine_name_cols[1].find('a').get_text())
            drug_type.append(drug_name_cols[3].get_text())
        except:
         print("EXCEPTION 3 OCCURED----------------------------------------------------------------------------")
         if len(drug_name_cols) >= 3  and drug_name_cols[1].find('a') and len(generic_medicine_name_cols)>= 2 and generic_medicine_name_cols[1].find('a')  :
          print("Generic Medicine : "+generic_medicine_name_cols[1].find('a').get_text()+" , Brand name : "+drug_name_cols[1].find('a').get_text()+" link : "+ brand_drug_link.get('href'))
         continue
     except:
      print("EXCEPTION 2 OCCURED----------------------------------------------------------------------------")
      if len(generic_medicine_name_cols)>= 2 and generic_medicine_name_cols[1].find('a')  :
       print("Generic Medicine : "+generic_medicine_name_cols[1].find('a').get_text())
      continue   
  except:
   print("EXCEPTION 1 OCCURED----------------------------------------------------------------------------")
   continue
finally:
 single_generic_medicines_data = pd.DataFrame({
          "generic_medicine_name": generic_medicines_names_list,
          "drug_name": drug_names_list,
          "drug_manufacturer": drug_manufacturer_list,
          "drug_type":drug_type})
 with open('C:/Users/I328284/Desktop/MyLearning/Innvent/SingleGeneric_BrandDrugs.csv', 'a') as f:
    single_generic_medicines_data.to_csv(f, header=False)
