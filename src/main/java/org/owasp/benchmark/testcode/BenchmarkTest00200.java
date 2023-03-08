/**
 * OWASP Benchmark Project v1.2
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project. For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author Nick Sanidas
 * @created 2015
 */
package org.owasp.benchmark.testcode;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/sqli-00/BenchmarkTest00200")
public class BenchmarkTest00200 extends HttpServlet {

    class Apple extends Fruit {
        public String price;
        public String price2;
        public Integer count;

        public Apple another;

        public void setCount(Integer v) {
            count = v;
            price = "asf";
        }

        // @Override
        // public void setCount2(Integer v) {}

        @Override
        public void setCount3(Integer v) {}

        @Override
        public void inject(String sql) {
            org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(sql);
        }
    }

    private static long serialVersionUID = 1L;

    private Apple apple = new Apple();
    private Fruit fruit = new Fruit();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public String modify(String sql) {
        return sql; // sql.replace("SELECT", "select");
    }

    public void useApple(Apple apple) {
        apple.count += 1;
    }

    public Integer useObj(Integer price) {
        price += 1;
        return price;
    }

    public Apple getApple() {
        apple.count = 1;
        return apple;
    }

    public Fruit getFruit(String str) {
        if (str.contains("fruit")) {
            return new Fruit();
        } else {
            return new Apple();
        }
    }

    public Fruit getFruit2(String str) {
        if (str.contains("fruit")) {
            return new Fruit();
        } else {
            return new Fruit();
        }
    }

    Apple getA(String sql) {
        Apple apple1 = new Apple();
        apple1.price = sql;
        return apple1;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Fruit obj = new Apple();

        apple.count = 1;
        apple.price = "1";
        apple.another.count = 1;

        apple.another.another.price = apple.another.another.another.price;

        this.getApple().count = 2;

        useObj(this.getApple().count);

        this.apple.setCount(2);
        this.apple.setCount2(2);
        this.apple.setCount3(2);

        Fruit.class.toString();

        fruit.setCount2(2);
        fruit.setCount3(2);

        obj.setCount3(2);
        obj.setCount2(2);

        getFruit(getInitParameter("123")).setCount3(2);

        getFruit2(getInitParameter("123")).setCount3(2);

        String param = null;
        if (request.getHeader("BenchmarkTest00200") != null) {
            param = request.getHeader("BenchmarkTest00200");
        }

        useApple(apple);
        // useObj(apple.count);

        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().

        serialVersionUID = 2;

        InputStream is =
                new InputStream() {
                    @Override
                    public int read() throws IOException {
                        return 0;
                    }
                };

        ZipInputStream zipInputStream = new ZipInputStream(is);

        GZIPInputStream gzipInputStream = new GZIPInputStream(is);

        byte[] b = new byte[10];
        is.read(b, 0, 10);

        String bar = "alsosafe";
        if (param != null) {
            param = java.net.URLDecoder.decode(param, "UTF-8");

            java.util.List<String> valuesList = new java.util.ArrayList<String>();
            valuesList.add("safe");
            valuesList.add(param);
            valuesList.add("moresafe");
            valuesList.add(String.valueOf(serialVersionUID));

            valuesList.remove(0); // remove the 1st safe value

            bar = valuesList.get(1) + "ace"; // get the last 'safe' value
        }

        Fruit fruit1 = null;
        bar = request.getParameter("password");
        String sql1 = null;
        if (Math.random() > 10) {
            sql1 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + bar + "'";
            this.apple.another.another.price = sql1;
            this.apple.another.another.price2 = "";
            fruit1 = new Fruit();
        } else {
            sql1 = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + "'";
            fruit1 = new Apple();
        }

        Apple fruit2 = this.apple.another;
        Apple fruit3 = this.apple.another.another;

        fruit3.price = ";";

        // fruit1.inject(fruit3.count.toString());

        // org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(getA(sql1).price);
        org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(fruit3.price);
        org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(fruit3.price2);
        org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(
                fruit3.count.toString());

        try {
            bar = request.getParameter("password");
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + bar + "'";
            // org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(sql1);

            if (Math.random() > 10) {
                sql = modify(sql);
                // org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate("123");

            }

            // org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(sql);
            response.getWriter()
                    .println(
                            "No results can be displayed for query: "
                                    + org.owasp.esapi.ESAPI.encoder().encodeForHTML(sql)
                                    + "<br>"
                                    + " because the Spring batchUpdate method doesn't return results.");
        } catch (org.springframework.dao.DataAccessException e) {
            if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
            } else throw new ServletException(e);
        }
    }
}
