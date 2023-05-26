import pandas as pd
import re
from clean_gadget import clean_gadget
from preprocess.lang_processors.cpp_processor import CppProcessor


def normalization(source):
    """
    normalization code
    :param source: dataframe
    :return:
    """
    cpp_processor = CppProcessor()
    nor_code = []
    for fun in source['code']:
        lines = fun.split('\n')
        # print(lines)
        code = ''
        for line in lines:
            line = line.strip()
            line = re.sub('//.*', '', line)
            code += line + ' '
        # code = re.sub('(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/', "", code)
        code = re.sub('/\\*.*?\\*/', '', code)
        code = clean_gadget([code])
        code[0] = re.sub('"".*?""', '', code[0], 20)
        code_list = cpp_processor.tokenize_code(code[0])
        print(len(code_list))

        tokenization_code = ''
        for token in code_list:
            tokenization_code = tokenization_code + token + " "
        nor_code.append(tokenization_code)
        # print(tokenization_code)
    return nor_code


def normalization2(source):
    cpp_processor = CppProcessor()
    nor_code = []
    for fun in source['code']:
        lines = fun.split('\n')
        # print(lines)
        code = ''
        for line in lines:
            line = line.strip()
            line = re.sub('//.*', '', line)
            code += line + ' '
        # code = re.sub('(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/', "", code)
        code = re.sub('/\\*.*?\\*/', '', code)
        code = clean_gadget([code])
        code[0] = re.sub('"".*?""', '', code[0], 20)

        code_list = cpp_processor.tokenize_code(code[0])
        # nor_code.append(code[0])
        # nor_code.append(code6)
        tokenization_code = ''
        for token in code_list:
            tokenization_code = tokenization_code + token + " "
        nor_code.append(tokenization_code)
        print(tokenization_code)
        with open('./corpus.txt', 'a') as f:
            f.write(tokenization_code)
            f.write('\n')
    return nor_code


def mutrvd():
    train = pd.read_pickle('trvd_train.pkl')
    test = pd.read_pickle('trvd_test.pkl')
    val = pd.read_pickle('trvd_val.pkl')

    train['code'] = normalization(train)
    train.to_pickle('./mutrvd/train.pkl')

    test['code'] = normalization(test)
    test.to_pickle('./mutrvd/test.pkl')

    val['code'] = normalization(val)
    val.to_pickle('./mutrvd/val.pkl')


def nor(source):
    cpp_processor = CppProcessor()
    nor_code = []
    lines = source.split('\n')
    # print(lines)
    code = ''
    for line in lines:
        line = line.strip()
        line = re.sub('//.*', '', line)
        code += line + ' '
    # code = re.sub('(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/', "", code)
    code = re.sub('/\\*.*?\\*/', '', code)
    code = clean_gadget([code])
    # code[0] = code[0].replace('"".*?""', '', 10)
    code[0] = re.sub('"".*?""', '', code[0], 20)

    code_list = cpp_processor.tokenize_code(code[0])
    tokenization_code = ''
    for token in code_list:
        tokenization_code = tokenization_code + token + " "
    nor_code.append(tokenization_code)
    # print(tokenization_code)
    return nor_code


if __name__ == '__main__':
    str = r"""
    static ngx_int_t
ngx_http_range_multipart_header(ngx_http_request_t *r,
    ngx_http_range_filter_ctx_t *ctx)
{
    size_t              len;
    ngx_uint_t          i;
    ngx_http_range_t   *range;
    ngx_atomic_uint_t   boundary;

    len = sizeof(CRLF ""--"") - 1 + NGX_ATOMIC_T_LEN
          + sizeof(CRLF ""Content-Type: "") - 1
          + r->headers_out.content_type.len
          + sizeof(CRLF ""Content-Range: bytes "") - 1;

    if (r->headers_out.content_type_len == r->headers_out.content_type.len
        && r->headers_out.charset.len)
    {
        len += sizeof(""; charset="") - 1 + r->headers_out.charset.len;
    }

    ctx->boundary_header.data = ngx_pnalloc(r->pool, len);
    if (ctx->boundary_header.data == NULL) {
        return NGX_ERROR;
    }

    boundary = ngx_next_temp_number(0);

    /*
     * The boundary header of the range:
     * CRLF
     * ""--0123456789"" CRLF
     * ""Content-Type: image/jpeg"" CRLF
     * ""Content-Range: bytes ""
     */

    if (r->headers_out.content_type_len == r->headers_out.content_type.len
        && r->headers_out.charset.len)
    {
        ctx->boundary_header.len = ngx_sprintf(ctx->boundary_header.data,
                                           CRLF ""--%0muA"" CRLF
                                           ""Content-Type: %V; charset=%V"" CRLF
                                           ""Content-Range: bytes "",
                                           boundary,
                                           &r->headers_out.content_type,
                                           &r->headers_out.charset)
                                   - ctx->boundary_header.data;

    } else if (r->headers_out.content_type.len) {
        ctx->boundary_header.len = ngx_sprintf(ctx->boundary_header.data,
                                           CRLF ""--%0muA"" CRLF
                                           ""Content-Type: %V"" CRLF
                                           ""Content-Range: bytes "",
                                           boundary,
                                           &r->headers_out.content_type)
                                   - ctx->boundary_header.data;

    } else {
        ctx->boundary_header.len = ngx_sprintf(ctx->boundary_header.data,
                                           CRLF ""--%0muA"" CRLF
                                           ""Content-Range: bytes "",
                                           boundary)
                                   - ctx->boundary_header.data;
    }

    r->headers_out.content_type.data =
        ngx_pnalloc(r->pool,
                    sizeof(""Content-Type: multipart/byteranges; boundary="") - 1
                    + NGX_ATOMIC_T_LEN);

    if (r->headers_out.content_type.data == NULL) {
        return NGX_ERROR;
    }

    r->headers_out.content_type_lowcase = NULL;

    /* ""Content-Type: multipart/byteranges; boundary=0123456789"" */

    r->headers_out.content_type.len =
                           ngx_sprintf(r->headers_out.content_type.data,
                                       ""multipart/byteranges; boundary=%0muA"",
                                       boundary)
                           - r->headers_out.content_type.data;

    r->headers_out.content_type_len = r->headers_out.content_type.len;

    r->headers_out.charset.len = 0;

    /* the size of the last boundary CRLF ""--0123456789--"" CRLF */

    len = sizeof(CRLF ""--"") - 1 + NGX_ATOMIC_T_LEN + sizeof(""--"" CRLF) - 1;

    range = ctx->ranges.elts;
    for (i = 0; i < ctx->ranges.nelts; i++) {

        /* the size of the range: ""SSSS-EEEE/TTTT"" CRLF CRLF */

        range[i].content_range.data =
                               ngx_pnalloc(r->pool, 3 * NGX_OFF_T_LEN + 2 + 4);

        if (range[i].content_range.data == NULL) {
            return NGX_ERROR;
        }

        range[i].content_range.len = ngx_sprintf(range[i].content_range.data,
                                               ""%O-%O/%O"" CRLF CRLF,
                                               range[i].start, range[i].end - 1,
                                               r->headers_out.content_length_n)
                                     - range[i].content_range.data;

        len += ctx->boundary_header.len + range[i].content_range.len
                                    + (size_t) (range[i].end - range[i].start);
    }

    r->headers_out.content_length_n = len;

    if (r->headers_out.content_length) {
        r->headers_out.content_length->hash = 0;
        r->headers_out.content_length = NULL;
    }

    return ngx_http_next_header_filter(r);
}
    """
    code = nor(str)
    print(code)
