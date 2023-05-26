from tree_sitter import Language, Parser
from preprocess.lang_processors.cpp_processor import CppProcessor


def selectFun(nodes, functions: list, code, flatten):
    """
    递归的从AST中抽取所有函数
    :param nodes:  AST节点
    :param functions: 函数list集合
    :return:
    """
    if len(nodes.children) == 0:
        return
    for child_node in nodes.children:
        if child_node.type == "function_definition":
            function_start_line = child_node.start_point[0]
            function_end_line = child_node.end_point[0]
            # 不在同一行
            if function_start_line != function_end_line:
                function_code = code[function_start_line:function_end_line + 1]
                if flatten:
                    function_code = " ".join(function_code)
                else:
                    function_code = "\n".join(function_code)
            else:
                function_code = code[function_start_line]
            functions.append(function_code)
        selectFun(child_node, functions, code, flatten)


def extract(path: str, language, flatten=False):
    """
    抽取文件中所有函数
    :param path: 文件所在路径
    :return: 所有函数list集合
    """
    # CPP_LANGUAGE = Language('./languages.so', 'cpp')
    LANGUAGEs = Language('C:\\Users\\Administrator\\PycharmProjects\\VulSystem\\preprocess\\languages.so', language)

    parser = Parser()
    parser.set_language(LANGUAGEs)
    with open(path) as f:
        codes = f.readlines()
    code = ''
    for line in codes:
        code += line
    tree = parser.parse(code.encode('utf-8').decode('unicode_escape').encode())
    root_node = tree.root_node
    functions = []
    # 为了确定起始行
    code = code.split("\n")
    # 保留原格式还是将其展平
    flatten = flatten
    selectFun(root_node, functions, code, flatten)
    return functions


if __name__ == '__main__':
    # 测试输入一个C或CPP文件
    funs = extract('./test2.cpp', 'cpp')

    cpp_processor = CppProcessor()
    for fun in funs:
        fun = cpp_processor.tokenize_code(fun)
        print(fun)
