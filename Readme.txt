En certs arxius dels tests hi ha una secció de codi com la següent:

// COOKIES
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()){ // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }

Aquesta part del codi pot fallar depenent de la pàgina web ja que pel que hem vist de tant en tant posen un popup per acceptar les cookies de la pàgina que no deixa fer una altre cosa fins que s'accepten i en altres dies aquest popup no apareix, per tant el test falla al inici.

En qualsevol cas, si apareix la secció d'acceptar cookies i no han cambiat el nom de la classe hauría de funcionar, en cas de que el test falli perque no apareix, s'ha de comentar el codi en el test a executar, i si apareix s'ha de descomentar.

El codi es pot trobar al inici de les seccions Given sobre la línia 35 o així