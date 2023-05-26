from flask import Flask, request
from preprocess.extractFunFromFile import extract
from preprocess.lang_processors.cpp_processor import CppProcessor
from normalization import nor

app = Flask(__name__)
cpp_processor = CppProcessor()


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/preprocess/<path:url>', methods=['GET', 'POST'])
def extractFun(url):
    print("path: ", url)
    functions = ''
    funs = extract(url, 'cpp', False)
    for fun in funs:
        tokenized_code = cpp_processor.tokenize_code(fun)
        fun = ''
        for token in tokenized_code:
            fun = fun + token + ' '
        functions = functions + fun + "####"
    return functions


@app.route('/normalize', methods=['GET', 'POST'])
def normalizationFun():
    code = request.args.get('normalize', '')
    print(code)
    functions = ''
    funs = code.split("####")
    res = []
    for fun in funs:
        res.append(nor(fun))

    return "####".join(res)


if __name__ == '__main__':
    app.run()
